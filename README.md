Less language is an extension of css and this project compiles it into regular css. It adds several dynamic features into css: variables, expressions, nested rules, and so on. 

The original compiler was written in JavaScript and is called [less.js](http://lesscss.org/). The less language is mostly defined in less.js documentation/issues and by what less.js actually do. Links to less.js:
* [less.js home page](http://lesscss.org/)
* [less.js source code & issues](https://github.com/cloudhead/less.js) 

Less4j is a port and its behavior should be as close to the original implementation as reasonable. Unless explicitely stated otherwise, any difference between less.js and less4j outputs is considered a bug. As close as reasonable means that style sheets generated by less4j must be functionally the same as the outputs of less.js. However, they do not have to be exactly the same:
* Behaviour of less.js and less4j may differ on invalid input files.
* Output files may differ by whitespaces or comments locations.
* Less4j may do more than less.js in some situations. The input rejected by less.js may be accepted and translated by less4j. 

All known differences are documented on [wiki page](https://github.com/SomMeri/less4j/wiki/Differences-Between-Less.js-and-Less4j). In the future, Less4j will produce warning any time it produce functionally different CSS. That feature was not implemented yet.

## Continuous Integration
Continuous integration is set up on [Travis-CI](http://travis-ci.org/SomMeri/less4j), its current status is: [![Build Status](https://secure.travis-ci.org/SomMeri/less4j.png)](http://travis-ci.org/SomMeri/less4j).

## Twitter
Our twitter account: [Less4j](https://twitter.com/Less4j)

## Documentation:
The documentation is kept on Github wiki:
* [wiki home page](https://github.com/SomMeri/less4j/wiki),
* [all written wiki pages](https://github.com/SomMeri/less4j/wiki/_pages). 

For those interested about project internals, architecture and comments handling are described in a [blog post] (http://meri-stuff.blogspot.sk/2012/09/tackling-comments-in-antlr-compiler.html). The blog post captures our ideas at the time of its writing, so current implementation be a bit different.

## API:
Project is still in alpha and current API is very temporary. It will change in the future. 

Access the copiler through the `String compile(String lessContent)` method of the `com.github.less4j.ILessCompiler` interface. The interface has only one implementation: `com.github.less4j.core.DefaultLessCompiler`.

Input string should contain the content of .less file and output string contains translated css style sheet:
<pre><code>ILessCompiler compiler = new DefaultLessCompiler();
String css = compiler.compile("* { margin: 1 1 1 1; }");
System.out.println(css);
</code></pre>

The method may throw either `IncorrectTreeException` or `CompileException` . Both are runtime and their message line and column numbers identifying source place that caused the error.

## TODO:
rewrite ended here

## In Progress:
*  CSS3 selectors - namespaces are open.

## Roadmap:
*  clean up unit tests,
*  write better README.mf,
*  add basic CSS hacks into it to see whether it is possible,
*  add error handling - at least reasonable basic version,
*  make it compatible with css2.1 and css3,
*  make API,
*  add less.js features and document the grammar while doing it,
*  finalize API.

## Links:
*  [http://www.w3.org/Style/CSS/specs.en.html]
*  [http://www.w3.org/Style/CSS/Test/CSS3/Selectors/current/]
*  [http://www.w3.org/TR/css3-selectors/] 
*  [http://www.w3.org/wiki/CSS3/Selectors]
*  [http://www.w3.org/TR/CSS2/]
*  [http://www.w3.org/TR/CSS2/syndata.html#numbers]
*  [http://www.w3.org/TR/2012/WD-css3-fonts-20120823/]
*  [http://www.w3.org/TR/CSS21/fonts.html]
*  Comparison of less and sass: [https://gist.github.com/674726]


