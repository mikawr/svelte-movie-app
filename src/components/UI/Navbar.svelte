<nav class="navbar p-5 w-full bg-primary flex justify-between">
    <div>
        <ul
            class="nav relative text-base-100 flex flex-col flex-wrap md:flex-row md:[&>li]:px-2"
        >
            <li><a href="/">Home</a></li>
            <li><a href="/movies">Movies</a></li>
            <li><a href="/members">Members</a></li>
            <li><a href="/contact">Contact</a></li>
        </ul>
    </div>

    <div class="justify-end">
        <ul
            class="relative text-base-100 flex flex-col flex-wrap sm:flex-row md:[&>li]:px-2"
        >
            <li>
<<<<<<< HEAD
                <a href="/login">
=======
                <a href="/signup">Sign Up</a>
            </li>
            <li>
                <a href="/signin">
>>>>>>> refs/remotes/origin/master
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" class="w-6 h-6">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 9V5.25A2.25 2.25 0 0013.5 3h-6a2.25 2.25 0 00-2.25 2.25v13.5A2.25 2.25 0 007.5 21h6a2.25 2.25 0 002.25-2.25V15M12 9l-3 3m0 0l3 3m-3-3h12.75" />
                    </svg>
                </a>
            </li>
        </ul>
    </div>
</nav>

<style>
    .nav a {
        color: #fff;
        position: relative;
        text-decoration: none;
    }

    .nav a::before {
        content: "";
        position: absolute;
        width: 100%;
        height: 4px;
        border-radius: 4px;
        background-color: #fff;
        bottom: 0;
        left: 0;
        transform-origin: right;
        transform: scaleX(0);
        transition: transform 0.3s ease-in-out;
    }

    .nav a:hover::before {
        transform-origin: left;
        transform: scaleX(1);
    }
</style>