import React from "react"
import { Menu } from "./Menu"

export function MenuCategory({ menus = [], onAddClick }) {
    return (
        <React.Fragment>
            {menus.map(v =>
                <li key={v.menuId} class="list-group-item d-flex mt-3">
                    <Menu {...v} onAddClick={onAddClick} />
                </li>
            )}
        </React.Fragment>
    )
}