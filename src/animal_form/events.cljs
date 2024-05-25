(ns animal-form.events
  (:require
   [re-frame.core :as re-frame]
   [animal-form.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

;; registrando um novo evento de atualizar o formulario
;; manipuladores de eventos.
(re-frame/reg-event-db
 ::update-form
 ;; db -> database, ignoraremos o primeiro valor pq será o nome do evento?
 (fn [db [_ id value-input]]
   (assoc-in db [:form id] value-input)))

;; (assoc-in {} [:form :key2] "val") ==> {:form {:key2 "val"}}


(re-frame/reg-event-db
 ::save-form
 (fn [db]
   (let [form-data (:form db)
         animals (get db :animals [])
         updated-animals (conj animals form-data)]
     (assoc db :animals updated-animals))))