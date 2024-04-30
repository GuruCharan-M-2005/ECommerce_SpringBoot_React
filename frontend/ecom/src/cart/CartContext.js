// CartContext.js
import axios from "axios";
import React, { createContext, useEffect, useState } from "react";

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  const addToCart = async(iem) => {
    const res=await axios.get("http://localhost:8080/api/cart");

    setCart(res.data);
    
  };
  
  useEffect(()=>{
    addToCart();
  },[])

  // const removeFromCart = (itemId) => {
  //   const updatedCart = cart.filter((item) => item.id !== itemId);
  //   setCart(updatedCart);
  //   alert(`Removed from cart`); 
  // };

  // const updateQuantity = (itemId, newQuantity) => {
  //   const updatedCart = cart.map((item) =>
  //     item.id === itemId ? { ...item, quantity: newQuantity } : item
  //   );
  //   setCart(updatedCart);
  // };

  return (
    <CartContext.Provider
      value={{ cart, /*addToCart, removeFromCart, updateQuantity, setCart */}}
    >
      {children}
    </CartContext.Provider>
  );
};
