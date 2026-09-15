import {
    Home,
    Search,
    Library,
    Heart,
} from "lucide-react";

import { NavLink } from "react-router-dom";

function MobileNav() {
    const items = [
        {
            name: "Home",
            path: "/",
            icon: Home,
        },
        {
            name: "Search",
            path: "/search",
            icon: Search,
        },
        {
            name: "Library",
            path: "/library",
            icon: Library,
        },
        {
            name: "Liked",
            path: "/liked",
            icon: Heart,
        },
    ];

    return (
        <nav className="md:hidden fixed bottom-0 left-0 right-0 h-16 bg-zinc-950 border-t border-zinc-800 z-[60]">

            <div className="h-full grid grid-cols-4">

                {items.map((item) => {
                    const Icon = item.icon;

                    return (
                        <NavLink
                            key={item.path}
                            to={item.path}
                            className={({ isActive }) =>
                                `flex flex-col items-center justify-center gap-1 text-xs transition ${
                                    isActive
                                        ? "text-white"
                                        : "text-gray-500 hover:text-gray-300"
                                }`
                            }
                        >
                            <Icon size={20} />

                            <span>
                {item.name}
              </span>
                        </NavLink>
                    );
                })}

            </div>

        </nav>
    );
}

export default MobileNav;