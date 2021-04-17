;; utilities ---------
(defn split-by-white
  [line]
  (clojure.string/split line #" "))

(defn trim [line]
  (clojure.string/trim line))

(defn parse-int [str-int]
  (Integer/parseInt str-int))
;; ---------------------


(defn ->input-model [_]
  (let [n (map parse-int (split-by-white (read-line)))
        as (map parse-int (split-by-white (read-line)))]
    [n as]))

(defn solve [[n as]]
  (loop [_as as
         acc 0]
    (if (apply = true (map even? _as))
      (recur (map #(/ % 2) _as) (inc acc))
      acc)))

(defn output [times]
  (println (format "%d" times)))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)
