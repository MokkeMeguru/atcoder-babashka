(ns problem
  (:require [clojure.string]))

;; utilities ---------
(defn split-by-keyword
  ([line]
   (split-by-keyword line #" "))
  ([line split-keyword]
   (clojure.string/split line split-keyword)))

(defn trim [line]
  (clojure.string/trim line))

(defn parse-int [str-int]
  (Integer/parseInt str-int))

(defn combination
  ([n k]
   (cond
     (or (zero? n) (zero? k)) []
     (< n k) []
     (= n k) (mapv vector (range n) (range n))
     :else (vec (filter some? (combination n k [] 0)))))
  ([n k candidate acc]
   (cond
     (= k (count candidate)) [candidate]
     (<= n acc) nil
     :else (concat (combination n k (conj candidate acc) (inc acc))
                   (combination n k candidate (inc acc))))))
;; ---------------------

(defn ->input-model [_])

(defn solve [input-model])

(defn output [output-model]
  (println ""))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)
