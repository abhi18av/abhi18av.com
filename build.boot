(set-env!
 :source-paths #{"src"}
 :resource-paths #{"resources" "assets"}
 :checkout '[[perun "0.4.3-SNAPSHOT"]]
 :dependencies '[[org.clojure/clojure "1.8.0"]
                 [perun "0.4.3-SNAPSHOT"]
                 [hiccup "1.0.5"]
                 [garden "1.3.2"]
                 [pandeiro/boot-http "0.7.3"]
                 [markdown-clj "1.0.1"]
                 [jeluard/boot-notify "0.1.2" :scope "test"]
                 [clj-http "2.3.0"]
                 [org.clojure/data.json "0.2.6"]
                 [deraen/boot-sass "0.3.1"]
                 [fsdb "0.2.1"]
                 [cpmcdaniel/boot-copy "1.0"]
                 [camel-snake-kebab "0.4.0"]])

(require '[boot.core :as boot]
         '[io.perun :as perun]
         '[io.perun.meta :as meta]
         '[io.perun.core :as perun-core]
         '[ok.index :as index-view]
         '[ok.post :as post-view]
         '[ok.data.technology]
         '[ok.data.project]
         '[pandeiro.boot-http :refer [serve]]
         '[garden.core :refer [css]]
         '[clojure.data.json]
         '[clj-http.client]
         '[fsdb.core :as fsdb]
         '[cpmcdaniel.boot-copy :refer :all]
         '[deraen.boot-sass :refer [sass]]
         '[clojure.string :refer [split]]
         '[clojure.java.shell :as shell])
;;'[jeluard.boot-notify :refer [notify]]

(defn is-of-type?
  [{:keys [permalink]} doc-type]
  (.startsWith permalink (str "/" doc-type)))

(deftask slack
  "Post `message` to slack."
  [u url URL str "The slack incoming webhook url"
   m message MESSAGE str "The slack message"]
  (with-post-wrap fileset
    (let [data {:username "GitLab CI"
                :text message
                :icon_emoji ":gitlabci:"}
          json (clojure.data.json/write-str data)]
      (clj-http.client/post url {:body json}))))

(deftask shellout
  "Run a shell command."
  [c cmd CMD str "The shell command to run."]
  (with-post-wrap fileset
    (apply shell/sh (split cmd #" "))))

(task-options!
 copy {:output-dir "target/public"
       ;; TODO: Make this regexp more readable. It has three parts:
       ;;       google search console + favicon stuff + other assets.
       :matching   #{#"(google.*\.html|safari-pinned-tab\.svg|favicon\.ico|browserconfig\.xml|manifest\.json|robots\.txt|\.htaccess)|\.(css|js|png|jpg|svg|gif|pdf|woff|woff2)$" #".well-known"}}
 slack {:url "https://hooks.slack.com/services/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"})

(defn slug-fn
  "Slugs are derived from filenames of html files. They can have a
  YYYY-MM-DD- prefix or not."
  [_ {:keys [filename]}]
  (last (re-find #"(\d+-\d+-\d+-|)(.*)\.html" filename)))

(deftask set-meta-data
  "Add :key attribute with :val value to each file metadata and also
   to the global meta"
  [k key VAL kw "meta-data key"
   v val VAL str "meta-data value"]
  (with-pre-wrap fileset
    (let [files           (meta/get-meta fileset)
          global-meta     (meta/get-global-meta fileset)
          updated-files   (map #(assoc % key val) files)
          new-global-meta (assoc global-meta key val)
          updated-fs      (meta/set-meta fileset updated-files)]
      (meta/set-global-meta updated-fs new-global-meta))))

(deftask categories
  "Add :categories of all posts to the meta-data"
  []
  (with-pre-wrap fileset
    (let [files           (filter #(is-of-type? % "posts")
                                  (meta/get-meta fileset))
          global-meta     (meta/get-global-meta fileset)
          categories      (filter #(:category %) files)
          updated-files   (map #(assoc % :categories categories) files)
          new-global-meta (assoc global-meta :categories categories)
          updated-fs      (meta/set-meta fileset updated-files)]
      (meta/set-global-meta updated-fs new-global-meta))))

(defn category-grouper
  [entries]
  (reduce (fn [result entry]
            (let [category (clojure.string/lower-case
                            (:category entry))
                  path (str category ".html")]
              (-> result
                  (update-in [path :entries] conj entry)
                  (assoc-in [path :entry :category] category))))
          {}
          entries))

(deftask fsdb
  "Loads fsdb and adds it as meta to the fileset."
  [f fsdb FSDB str "fsdb path"]
  (with-pre-wrap fileset
    (->> (fsdb/read-tree "manifest")
         ;; have to use :io.perun.global here to have it merged into
         ;; static's meta
         (assoc-in (meta fileset) [:io.perun.global :fsdb])
         (with-meta fileset))))

(deftask print-global-meta
  []
  (with-pre-wrap fileset
    (perun-core/report-info "print-global-meta" (meta fileset))
    fileset))

(defn my-grouper [group-key global-meta entries]
  (let [items (-> global-meta :fsdb :manifest group-key)]
    ;; TODO `apply merge map`, feels like it should use `into`
    (apply merge (map (fn [[key item]]
                        {(str (name key) ".html")
                         {:entries []
                          :entry nil
                          :meta global-meta
                          :meta-entry item}}) items))))

(deftask global-assortment
  "Renders an assortment using global metadata"
  [o out-dir    OUTDIR     str   "the output directory"
   r renderer   RENDERER   sym   "page renderer (fully qualified symbol resolving to a function)"
   k group-key  GROUPKEY   kw    "key on which to group"]
  (fn [next-task]
    (fn [fileset]
      (let [global-meta (io.perun.meta/get-global-meta fileset)
            task-fn (perun/assortment-task {:task-name "global-assortment"
                                            :renderer renderer
                                            :out-dir out-dir
                                            :filterer identity
                                            :sortby :date-published
                                            :comparator compare
                                            :grouper (partial my-grouper group-key global-meta)})]
        ((task-fn next-task) fileset)))))

(deftask check4programs
  "Checks whether externally required binaries are available."
  []
  (with-pre-wrap fileset
    (if-not (= 0 (:exit (shell/sh "which" "rsync")))
      (throw (Exception. "No rsync installed!"))
      (println "All required programs are installed!"))
    fileset))

(deftask build
  "Build the 200ok page."
  []
  (comp
   (perun/pandoc :extensions [".md" ".markdown"]
                 :cmd-opts ["-f" "markdown" "-t" "html5"])
   (perun/pandoc :extensions [".org"]
                 :cmd-opts ["-f" "org" "-t" "html5"]
                 :keep-yaml false)
   (perun/draft)
   ;;(print-meta)
   (perun/slug :slug-fn slug-fn)
   (perun/ttr)
   (categories)
   (perun/word-count)
   (perun/build-date)
   ;;(perun/gravatar
   ;; :source-key :author-email
   ;; :target-key :author-gravatar)

   (perun/paginate
    :renderer 'ok.index/render
    ;; Pagination numbering starts at `1` in Perun. Don't include
    ;; pagination info on the first page ("blog.html") and then start
    ;; with "blog-2.html".
    :slug-fn (fn [num] (if (= 1 num)
                         "blog"
                         (str "blog-" num)))
    :page-size 10
    :sortby :date-published
    :filterer #(is-of-type? % "posts"))

   (fsdb)
   ;;(print-global-meta)

   (perun/static :renderer 'ok.data.index/page        :page "index.html")
   (perun/static :renderer 'ok.data.people/page       :page "team.html")
   (perun/static :renderer 'ok.data.technologies/page :page "technologies.html")
   (perun/static :renderer 'ok.data.locations/page    :page "locations.html")
   (perun/static :renderer 'ok.data.clients/page      :page "clients.html")
   (perun/static :renderer 'ok.data.services/page     :page "services.html")
   (perun/static :renderer 'ok.data.projects/page     :page "projects.html")
   (perun/static :renderer 'ok.data.opensourced/page  :page "opensource.html")
   (perun/static :renderer 'ok.errors/page-old-ie     :page "ie-fallback.html")
   (perun/static :renderer 'ok.errors/page-404        :page "404.html")

   (global-assortment :renderer 'ok.data.technology/page
                      :out-dir "public/technology"
                      :group-key :technologies)

   (global-assortment :renderer 'ok.data.project/page
                      :out-dir "public/project"
                      :group-key :projects)

   ;; TODO: omit for prod
   (perun/static :renderer 'ok.data.debug/page :page "debug.html")

   ;; Groups all posts that have a :category (yes, only a single one
   ;; atm) into one file. For example a post with a :category of
   ;; "emacs" will be rendered into a file
   ;; "public/category/emacs.html" together with every other post
   ;; with the same tag.
   (perun/assortment :renderer 'ok.index/render
                     :grouper category-grouper
                     :sortby :date-published
                     :out-dir "public/category"
                     :filterer #(is-of-type? % "posts"))

   ;; renders each md file in `posts` into its own page
   (perun/render :renderer 'ok.post/render
                 :filterer #(is-of-type? % "posts"))

   (perun/render :renderer 'ok.page/render
                 :filterer #(is-of-type? % "audio-book"))

   (perun/sitemap)
   ;;(print-global-meta)

   (perun/atom-feed :filterer #(is-of-type? % "posts"))

   (perun/tags :renderer 'ok.tags/render
               :filterer #(is-of-type? % "posts")
               :out-dir "public/tags")

   ;;(notify)
   ;;(print-meta)
   (sass)
   (target)
   (copy) ; oldschool
   ;; newschool: rsyncs everything in assets/ to target/public/
   ;;(shellout :cmd "rsync -a assets/ target/public/")
   ))

(deftask dev
  []
  (comp
   (check4programs)
   (watch)
   (perun/global-metadata)
   (set-meta-data :key :target
                  :val "dev")
   (build)
   (serve :dir "target/public")))

(deftask prod
  []
  (comp
   (check4programs)
   ;;(slack :message "<http://200ok.ch|200ok.ch> has been updated.")
   (perun/global-metadata)
   (set-meta-data :key :target
                  :val "prod")
   (build)))
