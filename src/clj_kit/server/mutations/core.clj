(ns clj-kit.server.mutations.core
  (:require [com.wsscode.pathom3.connect.operation :as pco]
            [datalevin.core :as d]
            [clj-kit.server.database.core :refer [conn]]))

(pco/defmutation foo
  [{:keys [a]}]
  {::pco/op-name 'foo}
  (d/transact! conn []))

(def mutations [foo])
