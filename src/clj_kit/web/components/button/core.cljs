(ns clj-kit.web.components.button.core
  (:require [dumdom.core :refer [defcomponent]]
            [herb.core :refer [<class]]))

(defn style
  []
  {})

(defcomponent button
  [props & children]
  [:button (assoc props :class (<class style)) children])
