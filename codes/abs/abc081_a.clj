;; utilities ---------
(defn split-by-white
  [line]
  (clojure.string/split line #" "))

(defn trim [line]
  (clojure.string/trim line))
;; ---------------------


(defn ->input-model [_]
  (let [ss (map #(Integer/parseInt %) (clojure.string/split (read-line) #""))]
    ss))

(defn solve [ss]
  (apply + ss))

(defn output [output-model]
  (println (format "%d" output-model)))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)
