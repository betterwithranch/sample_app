(ns sample-app.root
  (:use
    [compojure.core :only (defroutes GET)]
    [compojure.route :only (not-found)]
    [joodo.middleware.view-context :only (wrap-view-context)]
    [joodo.views :only (render-template render-html)]
    [joodo.controllers :only (controller-router)]))

(defroutes sample-app-routes
  (GET "/" [] (render-template "index"))
  (controller-router 'sample-app.controller)
  (not-found (render-template "not_found" :template-root "sample_app/view" :ns `sample-app.view.view-helpers)))

(def app-handler
  (->
    sample-app-routes
    (wrap-view-context :template-root "sample_app/view" :ns `sample-app.view.view-helpers)))

