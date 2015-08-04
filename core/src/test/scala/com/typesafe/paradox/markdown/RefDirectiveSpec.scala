/*
 * Copyright © 2015 Typesafe, Inc. <http://www.typesafe.com>
 */

package com.typesafe.paradox.markdown

class RefDirectiveSpec extends MarkdownBaseSpec {

  "Ref directive" should "create links with html extension" in {
    markdown("@ref[Page](page.md)") shouldEqual html("""<p><a href="page.html">Page</a></p>""")
  }

  it should "support 'ref:' as an alternative name" in {
    markdown("@ref:[Page](page.md)") shouldEqual html("""<p><a href="page.html">Page</a></p>""")
  }

  it should "handle relative links correctly" in {
    markdown("@ref:[Page](../a/page.md)") shouldEqual html("""<p><a href="../a/page.html">Page</a></p>""")
  }

  it should "handle anchored links correctly" in {
    markdown("@ref:[Page](page.md#header)") shouldEqual html("""<p><a href="page.html#header">Page</a></p>""")
  }

  it should "retain whitespace before or after" in {
    markdown("This @ref:[Page](page.md#header) is linked.") shouldEqual html("""<p>This <a href="page.html#header">Page</a> is linked.</p>""")
  }

  it should "parse but ignore directive attributes" in {
    markdown("This @ref:[Page](page.md#header) { .ref a=1 } is linked.") shouldEqual html("""<p>This <a href="page.html#header">Page</a> is linked.</p>""")
  }

}