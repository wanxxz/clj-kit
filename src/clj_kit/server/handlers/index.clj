(ns clj-kit.server.handlers.index)

(defn index
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (slurp "resources/public/index.html")})
