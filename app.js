var jtrump = Block('div', 'jtrump');
jtrump
    .add(Block('block', 'intro')
        .add('text', 'title')
        .add ('text', 'author')
    )
    .add(Block('block', 'tabs')
        .add(Block('tab', 'explore')
            .on('click', function (e) {
                if (e.detail.noscroll != true) {
                    $(document.body).animate({
                        scrollTop: $('#jtrump').offset().top + 'px'
                    }, 600);
                    e.stopPropagation();
                }
            })
        )
        .add('tab', 'github')
    )
    .add(Block('div', 'content')
        .add(Block('div', 'explore')
            .on('show', function () {
                jtrump.child('content/explore').css('display', 'block');
            })
            .on('hide', function () {
                jtrump.child('content/explore').css('display', 'none');
            })
        )
        .add(Block('div', 'github')
            .add('text', 'title')
            .add('text', 'textA')
            .add('text', 'textB')
            .add('text', 'textC')
            .add(Block('break').data(2))
            .add(Block('block', 'buttons')
                .add(Block('block', 1)
                    .add(Block('a', 'link')
                        .add('image', 1)
                    )
                    .add(Block('div', 'content')
                        .add('text', 1)
                    )
                )
            )
            .add(Block('break').data(2))
            .add('text', 'textD')
            .add('text', 'textE')
            .add(Block('break').data(2))
            .add(Block('block', 'author')
                .add(Block('block', 1)
                    .add(Block('a', 'link')
                        .add('image', 1)
                    )
                    .add(Block('div', 'content')
                        .add('text', 1)
                    )
                    .add(Block('div', 'follow')
                        .add('text', 1)
                    )
                )
            )
            .add(Block('break').data(2))
            .add(Block('div', 'footer')
                .add('text', 'textA')
                .add('text', 'textB')
                .add('text', 'textC')
            )
            .on('show', function () {
                jtrump.child('content/github').css('display', 'table');
            })
            .on('hide', function () {
                jtrump.child('content/github').css('display', 'none');
            })
        )
    )
    .add(Block('div', 'copyright')
        .add('text', 'textA')
        .add(Block('text', 'year').data((new Date()).getFullYear().toString()))
        .add('text', 'textB')
        .add(Block('break').data(2))
    )
;

// display window
$(document).ready(function () {
    jtrump.load(function () {
        var content = jtrump.child('content');
        // load markdown from github readme
        $.ajax({
            url: 'https://raw.githubusercontent.com/anuvgupta/jtrump/master/README.md',
            success: function (data) {
                // resize window handler
                $(window).resize(function () {
                    var screenshot = $("img[alt='JTrump Screenshot']");
                    if (window.innerWidth < 1000)
                        screenshot.css('width', '95%');
                    else screenshot.css('width', 'auto');
                    if (window.innerWidth < 800)
                        content.css({
                            width: '92.6%',
                            borderRadius: '5px',
                            margin: '40px auto 20px auto'
                        });
                    else content.css({
                        width: '75%',
                        borderRadius: '10px',
                        margin: '40px auto'
                    });
                    var tabs = jtrump.child('tabs').children();
                    if (window.innerWidth < 550) {
                        jtrump.child('intro/title').css({
                            'font-size': '100px',
                            'margin-bottom': '30px'
                        });
                        for (tab in tabs) tabs[tab].on('small');
                    } else {
                        jtrump.child('intro/title').css({
                            'font-size': '120px',
                            'margin-bottom': '20px'
                        });
                        for (tab in tabs) tabs[tab].on('big');
                    }
                });
                var renderer = new marked.Renderer();
                content.child('explore').html(marked(data, { renderer: renderer })).css('opacity', '1');
                jtrump.fill(document.body);
                jtrump.child('tabs/explore').on('click', { noscroll: true });
                document.body.style.opacity = '1';
                $(window).resize();
                var index = window.location.href.indexOf('#');
                if (index != -1) {
                    var id = window.location.hash;
                    if (id == '#source' || id == '#source-code')
                        jtrump.child('tabs/github').on('click', { noscroll: true });
                    if (document.getElementById(id.substring(1)) != null) {
                        $(document.body).animate({
                            scrollTop: $(id).offset().top + 'px'
                        }, /* 700 */ 0);
                    }
                  }
            }
        });
        // load github buttons
        jtrump.child('content/github/buttons/block/content')
            .html('<a class="github-button" href="https://github.com/anuvgupta/JTrump" data-icon="octicon-star" data-style="mega" data-count-href="/anuvgupta/JTrump/stargazers" data-count-api="/repos/anuvgupta/JTrump#stargazers_count" data-count-aria-label="# stargazers on GitHub" aria-label="Star anuvgupta/JTrump on GitHub">Star</a><br/>', true)
            .html('<a class="github-button" href="https://github.com/anuvgupta/JTrump/fork" data-icon="octicon-repo-forked" data-style="mega" data-count-href="/anuvgupta/JTrump/network" data-count-api="/repos/anuvgupta/JTrump#forks_count" data-count-aria-label="# forks on GitHub" aria-label="Fork anuvgupta/JTrump on GitHub">Fork</a><br/>', true)
            .html('<a class="github-button" href="https://github.com/anuvgupta/JTrump" data-icon="octicon-eye" data-style="mega" data-count-href="/anuvgupta/JTrump/watchers" data-count-api="/repos/anuvgupta/JTrump#subscribers_count" data-count-aria-label="# watchers on GitHub" aria-label="Watch anuvgupta/JTrump on GitHub">Watch</a><br/>', true)
        ;
        jtrump.child('content/github/author/block/follow')
            .html('<a class="github-button" href="https://github.com/anuvgupta" data-style="mega" data-count-href="/anuvgupta/followers" data-count-api="/users/anuvgupta#followers" data-count-aria-label="# followers on GitHub" aria-label="Follow @anuvgupta on GitHub">Follow Me</a>', true)
        ;
        $.getScript({
            async: false,
            url: 'https://buttons.github.io/buttons.js'
        });
    }, 'app', 'jQuery');
});
