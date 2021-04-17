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
