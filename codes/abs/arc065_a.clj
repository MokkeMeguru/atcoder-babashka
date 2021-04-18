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

(defn ->input-model [_]
  (vec (trim (read-line))))

(defn solve [S]
  (let [start-8 (apply str (take 8 S))]
    (cond
      (zero? (count start-8)) true
      (> 5 (count start-8)) false
      (clojure.string/starts-with? start-8 "dreamera") (solve (drop 5 S))
      (clojure.string/starts-with? start-8 "dreamer") (solve (drop 7 S))
      (clojure.string/starts-with? start-8 "dream") (solve (drop 5 S))
      (clojure.string/starts-with? start-8 "eraser") (solve (drop 6 S))
      (clojure.string/starts-with? start-8 "erase") (solve (drop 5 S))
      :else false)))

(defn output [solve?]
  (println (if solve? "YES" "NO")))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)
