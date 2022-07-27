(ns clj-kit.web.components.field.text
  (:require [dumdom.core :refer [defcomponent]]
            [herb.core :refer [<class]]))

;; input
(defn input-style
  []
  {})

(defcomponent input
  [{:keys [id name value type error info] :or {type "text"}}]
  [:input {:id id :name name :value value :type type}
   [:span {} info]
   [:span {} error]])

;; label
(defn label-style
  []
  {})

(defcomponent label
  [{:keys [label id]}]
  [:label {:for id :class (<class label-style)} label])

;; text
(defn text-style
  []
  {})

(defcomponent text
  [props]
  [:div {:class (<class text-style)}
   [label props]
   [input props]])
