import {
    Home,
    Search,
    Library,
    Heart,
    Plus,
    LogOut,
} from "lucide-react";

import { NavLink } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

function Sidebar() {
    const { user, logout } = useAuth();

    const navItems = [
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
            name: "Your Library",
            path: "/library",
            icon: Library,
        },
    ];

    return (
        <aside className="w-64 min-h-screen bg-zinc-950 border-r border-zinc-800 flex flex-col">

            {/* Logo */}
            <div className="px-6 py-6">
                <h1 className="text-2xl font-bold">
                    TuneFlow 🎵
                </h1>
            </div>

            {/* Navigation */}
            <nav className="px-3 space-y-1">

                {navItems.map((item) => {

                    const Icon = item.icon;

                    return (
                        <NavLink
                            key={item.path}
                            to={item.path}
                            className={({ isActive }) =>
                                `flex items-center gap-4 px-4 py-3 rounded-lg transition ${
                                    isActive
                                        ? "bg-zinc-800 text-white"
                                        : "text-gray-400 hover:text-white hover:bg-zinc-900"
                                }`
                            }
                        >
                            <Icon size={20} />

                            <span className="font-medium">
                {item.name}
              </span>
                        </NavLink>
                    );

                })}

            </nav>

            {/* Library Actions */}
            <div className="px-3 mt-8">

                <p className="px-4 mb-3 text-xs uppercase tracking-wider text-gray-500">
                    Your Music
                </p>

                <button className="w-full flex items-center gap-4 px-4 py-3 rounded-lg text-gray-400 hover:text-white hover:bg-zinc-900 transition">
                    <Plus size={20} />
                    <span>Create Playlist</span>
                </button>

                <button className="w-full flex items-center gap-4 px-4 py-3 rounded-lg text-gray-400 hover:text-white hover:bg-zinc-900 transition">
                    <Heart size={20} />
                    <span>Liked Songs</span>
                </button>

            </div>

            {/* User */}
            <div className="mt-auto border-t border-zinc-800 p-4">

                <div className="flex items-center gap-3 mb-4">

                    <div className="w-10 h-10 rounded-full bg-zinc-700 flex items-center justify-center">
                        {user?.name?.charAt(0)?.toUpperCase() || "U"}
                    </div>

                    <div className="min-w-0">

                        <p className="text-sm font-medium truncate">
                            {user?.name || "User"}
                        </p>

                        <p className="text-xs text-gray-500 truncate">
                            {user?.email || ""}
                        </p>

                    </div>

                </div>

                <button
                    onClick={logout}
                    className="w-full flex items-center gap-3 px-3 py-2 rounded-lg text-gray-400 hover:text-red-400 hover:bg-zinc-900 transition"
                >
                    <LogOut size={18} />
                    <span className="text-sm">
            Logout
          </span>
                </button>

            </div>

        </aside>
    );
}

export default Sidebar;