(ns clj-kit.web.pages.index
  (:require [dumdom.core :refer [defcomponent]]
            [herb.core :refer [<class]]))

(defn style
  []
  {:display "grid"})

(defcomponent index
  []
  [:div {:class (<class style)} "index content"])
