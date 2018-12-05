;; https://adventofcode.com/2018/day/2
(ns puzzle2
  (:require [clojure.string :as str]))

(defn counter-reducer [count-result, character]
  (assoc count-result character (inc (get count-result character 0))))

(defn letter-counter [x]
  (reduce counter-reducer {} (str/split x #"")))

(assert (= {"t" 2, "s" 1, "e", 1} (letter-counter "test")))

(defn has-n-letters [x num]
  (let [counter (letter-counter x)
        counter-values (vals counter)]
    (> (count (filter (fn [x] (= x num)) counter-values)) 0)))

(defn has-2-letters [x] (has-n-letters x 2))

(defn has-3-letters [x] (has-n-letters x 3))

(defn checksum [input]
  (let [strings-with-2 (count (filter has-2-letters input))
        strings-with-3 (count (filter has-3-letters input))]
    (* strings-with-2 strings-with-3)))

(def test-input
  '("abcdef" "bababc" "abbcde" "abcccd" "aabcdd" "abcdee" "ababab"))

(assert (= 12 (checksum test-input)))

(def parsed-input
  (str/split-lines (slurp "inputs/puzzle2")))

(defn -main []
  (println (checksum parsed-input)))
