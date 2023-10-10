<div
    class="hero min-h-screen"
    style="background-image: url(http://www.officialpsds.com/images/stocks/Movie-Theater-stock1947.jpg);"
>
    <div class="hero-overlay bg-opacity-60" />
    <div class="hero-content text-center text-neutral-content">
        <div class="max-w-md">
            <h1 class="mb-5 text-5xl font-bold">Movie King</h1>
            <p class="mb-5">Movies, TV shows, reviews, ratings, and more.</p>
        </div>
    </div>
</div>

<style>
    p {
        border-right: solid 3px #fff;
        white-space: nowrap;
        overflow: hidden;
        font-size: 28px;
        color: rgba(255, 255, 255, 0.7);
    }

    /* Animation */
    p {
        animation: animated-text 4s steps(29, end) 1s 1 normal both,
            animated-cursor 600ms steps(29, end) infinite;
    }

    /* text animation */

    @keyframes animated-text {
        from {
            width: 0;
        }
        to {
            width: 472px;
        }
    }

    /* cursor animations */

    @keyframes animated-cursor {
        from {
            border-right-color: #fff;
        }
        to {
            border-right-color: transparent;
        }
    }
</style>
