;; utilities ---------
(defn split-by-white
  [line]
  (clojure.string/split line #" "))

(defn trim [line]
  (clojure.string/trim line))
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
