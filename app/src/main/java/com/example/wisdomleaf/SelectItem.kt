package com.example.wisdomleaf

/**
 * This interface will act as a call back listener when we click on recyclerview item.
 */
interface SelectItem {
    /**
     * This method will return the position when we clicked on recyclerview item.
     */
    fun onSelectItem(pos: Int)
}