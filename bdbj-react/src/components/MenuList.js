import React from "react"
import { MenuCategory } from "./MenuCategory"

export function MenuList({ menus = [], categories = [], onAddClick }) {
    const categoryMap = new Map();
    categories.forEach(c => categoryMap.set(c, []));
    menus.forEach(m => categoryMap.get(m.category).push(m));
    return (
        <React.Fragment>
            <ul class="list-group products">
                {categories.map(c =>
                    <div key={c}>
                        <b>{c}</b>
                        <MenuCategory menus={categoryMap.get(c)} onAddClick={onAddClick} />
                    </div>
                )}
            </ul>
        </React.Fragment >
    )
}