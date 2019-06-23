(ns io.perun.example.stylesheet
  (:require [garden.def :as gdn]))

(gdn/defstyles screen
  [:body
   {:font-family "Helvetica Neue"
    :font-size   "16px"
    :line-height 1.5}])

