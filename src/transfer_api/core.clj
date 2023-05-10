(ns transfer-api.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as mj]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [transfer-api.handlers :refer :all]
            [transfer-api.transfer.database :as d])
  (:gen-class))

(defroutes app-routes
  (GET "/verify-user" [] (mj/wrap-json-body verify-user-handler {:keywords? true :bigdecimals? true}))
  (POST "/create-user" [] (mj/wrap-json-body create-user-handler {:keywords? true :bigdecimals? true}))
  (POST "/create-pix-key" [] (mj/wrap-json-body create-pix-key-handler {:keywords? true :bigdecimals? true}))
  (POST "/pix" [] (mj/wrap-json-body pix-transfer-handler {:keywords? true :bigdecimals? true}))
  (POST "/ted" [] (mj/wrap-json-body ted-transfer-handler {:keywords? true :bigdecimals? true})))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [port 3000]
    (server/run-server  (wrap-defaults #'app-routes api-defaults)  {:port port})
    (println (str "Running service on port " port))))