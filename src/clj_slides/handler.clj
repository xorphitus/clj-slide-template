(ns clj-slides.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :as hiccup]
            [hiccup.page :as hiccup-page]
            [clj-slides.slides :as slides]))

(defn publish [title description author slides]
  (hiccup/html
   (hiccup-page/doctype :html5)
   [:html {:lang "ja"}
    [:head
     [:meta {:charset "utf-8"}]
     [:title title]
     [:meta {:name "description" :content description}]
     [:meta {:name "author" :content author}]
     [:meta {:name "apple-mobile-web-app-capable" :content "yes"}]
     [:meta {:name "apple-mobile-web-app-status-bar-style" :content "black-translucent"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"}]
     [:link {:rel "stylesheet" :href "reveal.js/css/reveal.min.css"}]
     [:link {:rel "stylesheet" :href "reveal.js/css/theme/default.css" :id "theme"}]
     ;; For syntax highlighting
     [:link {:rel "stylesheet" :href "reveal.js/lib/css/zenburn.css"}]
     ;; If the query includes 'print-pdf', use the PDF print sheet
     [:script
      "document.write( '<link rel=\"stylesheet\" href=\"reveal.js/css/print/' + ( window.location.search.match( /print-pdf/gi ) ? 'pdf' : 'paper' ) + '.css\" type=\"text/css\" media=\"print\">' );"]
     "<!--[if lt IE 9]>"
     [:script {:src "reveal.js/lib/js/html5shiv.js"}]
     "<![endif]-->"]
    [:body
     [:div.reveal
      ;; Any section element inside of this container is displayed as a slide
      (into [:div.slides] slides)]
     [:script {:src "reveal.js/lib/js/head.min.js"}]
     [:script {:src "reveal.js/js/reveal.min.js"}]
     [:script
      "
// Full list of configuration options available here:
// https://github.com/hakimel/reveal.js#configuration
Reveal.initialize({
  controls: true,
  progress: true,
  history: true,
  center: true,
  theme: Reveal.getQueryHash().theme, // available themes are in /css/theme
  transition: Reveal.getQueryHash().transition || 'default', // default/cube/page/concave/zoom/linear/fade/none

  // Parallax scrolling
  // parallaxBackgroundImage: 'https://s3.amazonaws.com/hakim-static/reveal-js/reveal-parallax-1.jpg',
  // parallaxBackgroundSize: '2100px 900px',

  // Optional libraries used to extend on reveal.js
  dependencies: [
    { src: 'reveal.js/lib/js/classList.js', condition: function() { return !document.body.classList; } },
    { src: 'reveal.js/plugin/markdown/marked.js', condition: function() { return !!document.querySelector( '[data-markdown]' ); } },
    { src: 'reveal.js/plugin/markdown/markdown.js', condition: function() { return !!document.querySelector( '[data-markdown]' ); } },
    { src: 'reveal.js/plugin/highlight/highlight.js', async: true, callback: function() { hljs.initHighlightingOnLoad(); } },
    { src: 'reveal.js/plugin/zoom-js/zoom.js', async: true, condition: function() { return !!document.body.classList; } },
    { src: 'reveal.js/plugin/notes/notes.js', async: true, condition: function() { return !!document.body.classList; } }
  ]
});"]]]))

(defroutes app-routes
  (GET "/" [] (publish slides/title slides/description slides/author slides/contents))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
