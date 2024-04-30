import React, { useState, useEffect } from "react";
import axios from "axios";

export default function ShoppingDetails({ category }) {
  const [items, setItems] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      await axios
        .get(`http://localhost:8080/api/type/${category}`)
        .then((res) => {
          setItems(res.data);
          console.log(items);
        })
        .catch((err) => console.log(err));
    };
    fetchData();
  }, [category]);

  return (
    <div>
      {items.map((item) => (
        <div key={item.id}>
          <h1>{item.name}</h1>
        </div>
      ))}
    </div>
  );
}
