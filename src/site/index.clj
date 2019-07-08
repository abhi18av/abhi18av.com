(ns site.index
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (html5)]))


(defn render [{global-meta :meta posts :entries}]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
    [:head
          [:meta {:charset "utf-8"}]
          [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
          [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
          [:title "Home | Abhinav Sharma"]
          [:meta {:name "description" :content "Full Stack Web Engineer"}]
          [:meta {:name "author" :content "Abhinav Sharma"}]
          [:link {:rel "canonical" :href "https://abhi18av.com/"}]
          [:link {:rel "stylesheet" :type "text/css" :href "style.css"}]]
    [:body
       [:div {:class "wrapper-large"}
        [:div {:class "page home"}
         [:header {:class "header-home animated"}
          [:a {:class "link", :href "https://abhi18av.com/about"}
           [:img {:alt "Abhinav Sharma", :class "selfie", :src "profile.jpg"}]]
          [:h1 {:class "title"} "Abhinav Sharma"]
          [:h2 {:class "description"} "Full Stack Web Engineer"]
          [:div {:class "social-links"}
           ;; TODO replace this with angel list
           [:a {:class "link", :data-title "twitter.com/abhi18av", :href "http://twitter.com/abhi18av", :target "_blank"}
            [:svg {:class "icon icon-twitter"}
             [:use {:xlink:href "#icon-twitter"}]]]
           [:a {:class "link", :data-title "linkedin.com/in/abhi18av", :href "http://linkedin.com/in/abhi18av", :target "_blank"}
            [:svg {:class "icon icon-linkedin"}
             [:use {:xlink:href "#icon-linkedin"}]]]
           [:a {:class "link", :data-title "youtube.com/abhi18av", :href "http://youtube.com/abhi18av", :target "_blank"}
            [:svg {:class "icon icon-youtube"}
             [:use {:xlink:href "#icon-youtube"}]]]
           [:a {:class "link", :data-title "github.com/abhi18av", :href "https://github.com/abhi18av", :target "_blank"}
            [:svg {:class "icon icon-github"}
             [:use {:xlink:href "#icon-github"}]]]
           [:a {:class "link", :data-title "medium.com/@abhi18av", :href "https://medium.com/@abhi18av", :target "_blank"}
            [:svg {:class "icon icon-medium"}
             [:use {:xlink:href "#icon-medium"}]]]]]
         [:nav {:class "nav-home"}
          [:ul {:class "list"}
           [:li {:class "item"}
            [:a {:class "link", :href "./blog.html"} "Blog"]]
           [:li {:class "item"}
            [:a {:class "link", :href "./projects.html"} "Projects"]]
           [:li {:class "item"}
            [:a {:class "link", :href "./resume.html"} "Resume"]]
           [:li {:class "item"}
            [:a {:class "link", :href "./contact.html"} "Contact"]]]]
         [:h4 " I am passionate about helping startups launch and scale. "]
         ;; TODO Add links to the original technologies
         [:h5 " Here's my programming skillset"]
         [:ul {:class "skill-list"}
          [:li "ReactJS based Frontend Development"]
          [:li "Phaser GameEngine for the Web"]
          [:li "JavaScript"]
          [:li "ClojureScript based Electron Apps"]
          [:li "Go based backends and CLI apps"]
          [:li "Python Data Science"]
          [:li "Clojure"]
          [:li "Relational and Non-relational"]]

         [:footer {:class "footer-main"} " Abhinav Sharma © 2019 "
          [:a {:class "link", :href "https://abhi18av.com/feed.xml", :target "_blank"}
           [:svg {:class "icon icon-rss"}
            [:use {:xlink:href "#icon-rss"}]]]]]]
       [:svg {:display "none", :version "1.1", :xmlns "http://www.w3.org/2000/svg", :xmlns:xlink "http://www.w3.org/1999/xlink"}
        [:defs
         [:symbol {:id "icon-rss", :viewbox "0 0 1024 1024"}
          [:title "rss"]
          [:path {:class "path1", :d "M122.88 122.88v121.19c362.803 0 656.896 294.195 656.896 656.998h121.293c0-429.773-348.416-778.189-778.189-778.189zM122.88 365.414v121.293c228.813 0 414.362 185.498 414.362 414.413h121.242c0-295.834-239.821-535.706-535.603-535.706zM239.053 668.621c-64.205 0-116.224 52.122-116.224 116.275s52.019 116.224 116.224 116.224 116.173-52.019 116.173-116.224-51.968-116.275-116.173-116.275z"}]]

         ;; TODO Add angel list
         [:symbol {:id "icon-twitter", :viewbox "0 0 1024 1024"}
          [:title "twitter"]
          [:path {:class "path1", :d "M886.579 319.795c0.41 8.294 0.563 16.691 0.563 24.986 0 255.488-194.406 549.99-549.888 549.99-109.21 0-210.739-32-296.294-86.886 15.155 1.792 30.515 2.714 46.080 2.714 90.624 0 173.926-30.925 240.026-82.688-84.531-1.587-155.955-57.395-180.531-134.195 11.776 2.202 23.91 3.379 36.352 3.379 17.664 0 34.765-2.304 50.944-6.707-88.422-17.818-155.034-95.898-155.034-189.594 0-0.819 0-1.587 0-2.406 26.061 14.49 55.91 23.194 87.552 24.218-51.866-34.714-86.016-93.798-86.016-160.922 0-35.379 9.523-68.608 26.214-97.178 95.283 116.992 237.773 193.894 398.387 201.984-3.277-14.182-4.966-28.877-4.966-44.083 0-106.701 86.477-193.178 193.229-193.178 55.603 0 105.83 23.398 141.107 60.979 43.981-8.704 85.35-24.781 122.726-46.899-14.438 45.107-45.107 82.995-84.992 106.906 39.117-4.71 76.288-15.002 111.002-30.413-25.907 38.81-58.675 72.806-96.461 99.994z"}]]
         [:symbol {:id "icon-github", :viewbox "0 0 1024 1024"}
          [:title "github"]
          [:path {:class "path1", :d "M674.816 579.021c-36.762 0-66.56 41.318-66.56 92.109 0 50.893 29.798 92.211 66.56 92.211s66.56-41.318 66.56-92.211c-0.051-50.79-29.798-92.109-66.56-92.109zM906.547 339.251c7.629-18.688 7.936-124.877-32.512-226.611 0 0-92.723 10.189-233.011 106.496-29.44-8.192-79.258-12.186-128.973-12.186-49.818 0-99.584 3.994-129.024 12.186-140.339-96.307-233.062-106.496-233.062-106.496-40.397 101.734-39.987 207.923-32.461 226.611-47.514 51.61-76.544 113.613-76.544 198.195 0 367.923 305.306 373.811 382.31 373.811 17.51 0 52.122 0.102 88.781 0.102 36.608 0 71.27-0.102 88.678-0.102 77.107 0 382.31-5.888 382.31-373.811 0-84.582-28.979-146.586-76.493-198.195zM513.434 866.048h-2.867c-193.075 0-343.501-22.989-343.501-210.688 0-45.005 15.872-86.682 53.606-121.293 62.822-57.702 169.216-27.187 289.894-27.187 0.512 0 1.024 0 1.485 0 0.512 0 0.922 0 1.382 0 120.678 0 227.123-30.515 289.997 27.187 37.632 34.611 53.504 76.288 53.504 121.293 0 187.699-150.374 210.688-343.501 210.688zM349.235 579.021c-36.762 0-66.56 41.318-66.56 92.109 0 50.893 29.798 92.211 66.56 92.211 36.813 0 66.611-41.318 66.611-92.211 0-50.79-29.798-92.109-66.611-92.109z"}]]
         [:symbol {:id "icon-youtube", :viewbox "0 0 1024 1024"}
          [:title "youtube"]
          [:path {:class "path1", :d "M512 117.76c-503.194 0-512 44.749-512 394.24s8.806 394.24 512 394.24 512-44.749 512-394.24-8.806-394.24-512-394.24zM676.096 529.101l-229.888 107.315c-20.122 9.318-36.608-1.126-36.608-23.347v-202.138c0-22.17 16.486-32.666 36.608-23.347l229.888 107.315c20.122 9.421 20.122 24.781 0 34.202z"}]]
         [:symbol {:id "icon-linkedin", :viewbox "0 0 1024 1024"}
          [:title "linkedin"]
          [:path {:class "path1", :d "M256 153.6c0 54.374-36.352 101.171-102.451 101.171-62.208 0-102.349-44.134-102.349-98.509 0-55.808 38.912-105.062 102.4-105.062s101.171 46.592 102.4 102.4zM51.2 972.8v-665.6h204.8v665.6h-204.8z"}]
          [:path {:class "path2", :d "M358.4 534.733c0-79.104-2.611-145.203-5.222-202.291h184.013l9.114 88.218h3.891c25.907-41.523 89.395-102.4 195.686-102.4 129.638 0 226.918 86.784 226.918 273.51v381.030h-204.8v-351.283c0-81.613-31.078-143.872-102.4-143.872-54.374 0-81.613 44.032-95.898 80.333-5.222 13.005-6.502 31.13-6.502 49.306v365.517h-204.8v-438.067z"}]]
         [:symbol {:id "icon-medium", :viewbox "0 0 179.2 179.2"}
          [:title "medium"]
          [:path {:d "M597 1115v-1173q0 -25 -12.5 -42.5t-36.5 -17.5q-17 0 -33 8l-465 233q-21 10 -35.5 33.5t-14.5 46.5v1140q0 20 10 34t29 14q14 0 44 -15l511 -256q3 -3 3 -5zM661 1014l534 -866l-534 266v600zM1792 996v-1054q0 -25 -14 -40.5t-38 -15.5t-47 13l-441 220zM1789 1116 q0 -3 -256.5 -419.5t-300.5 -487.5l-390 634l324 527q17 28 52 28q14 0 26 -6l541 -270q4 -2 4 -6z", :transform "scale(0.1,-0.1) translate(0,-1536)"}]]]]]))
