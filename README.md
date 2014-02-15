# Clojure presentation slide template

[reveal.js][1]を用いたプレゼンテーション資料テンプレート。

素HTMLをもう書きたくない、どうせならClojureで、という人向け。

[1]: https://github.com/hakimel/reveal.js/

## 事前準備

[Leiningen][2] 1.7.0 以上をインストールしておく。

[2]: https://github.com/technomancy/leiningen


## 使い方

### 起動するために

まず、このテンプレートをcloneしてくる。

次に、git submoduleとして追加してある[reveal.js][1]をcloneする。

最後に、以下のコマンドを打つと http://localhost:3000 で資料が見られる。

    lein ring server

portを変更したい等の場合は、[ring][3]の仕様に従う。

[3]: https://github.com/weavejester/lein-ring

### 編集方法

`src/slc_slides/slides.clj`で`def`されている変数を編集する。

どの変数が何を指しているかは、命名からお察し下さい。

HTMLの出力には[hiccup][4]を使っている。

[4]: https://github.com/weavejester/hiccup

記法が[hiccup][4]なだけで、編集方法は[reveal.js][1]そのまま。

`div.slides`以下を`contents`変数として記述すればよい。

## License

Copyright © 2014 xorphitus
