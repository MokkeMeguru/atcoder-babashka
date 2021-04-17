;; utilities ---------
(defn split-by-white
  [line]
  (clojure.string/split line #" "))

(defn trim [line]
  (clojure.string/trim line))
;; ---------------------

(defn ->input-model [_]
  (let [[a b] (->> (read-line) trim split-by-white (map #(Integer/parseInt %)))]
    [a b]))

(defn solve [[a b]]
  (even? (* a b)))

(defn output [even?]
  (if even?
    (println "Even")
    (println "Odd")))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)
