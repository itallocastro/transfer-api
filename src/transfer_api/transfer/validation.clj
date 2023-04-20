(ns transfer-api.transfer.validation
  (:require [clojure.string :as str]
            [compojure.core :refer :all]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log])
  (:gen-class))

(def minimum-value 0)

(defn validate-balance
  "validate transfer operation balance should be >= value"
  [balance value]
  (log/info balance)
  (log/info value)
  (log/info (<= minimum-value balance))
  (log/info (<= minimum-value value))
  (log/info (<= value balance))
  (and
   (<= minimum-value balance)
   (<= minimum-value value)
   (<= value balance)))
