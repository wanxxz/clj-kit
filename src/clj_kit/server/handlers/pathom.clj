(ns clj-kit.server.handlers.pathom
  (:require
   [com.wsscode.pathom3.connect.indexes :as pci]
   [com.wsscode.pathom3.interface.eql :as pie]
   [clj-kit.server.resolvers.core :refer [resolvers]]
   [clj-kit.server.mutations.core :refer [mutations]]))

(def env
  (pci/register (into [] (concat mutations resolvers))))

(defn pathom
  [request]
  (let [args (:edn-params request)
        r (pie/process env args)]
    {:status 200 :body r}))
