(ns site.stylesheet
  (:require [garden.def :as gdn]))

;; TODO port the styles.css to Garden syntax
(gdn/defstyles screen
  [:body
   {:font-family "Helvetica Neue"
    :font-size   "16px"
    :line-height 1.5}])

