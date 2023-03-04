import { useEffect, useState } from "react"
import { StarIcon } from "@heroicons/react/solid";
import Image from "next/image";
import Currency from 'react-currency-formatter'
import { v4 as uuidv4 } from 'uuid';

const MIN_RATING = 1;
const MAX_RATING = 5;

export default function Product({ id, title, price, description, category, image, rating}) {
  
  const [hasPrime, sethasPrime] = useState(false);

  useEffect(() => {
    sethasPrime(Math.random() < 0.5);
  }, []);

  return (
    <div className="relative flex-col m-5 bg-white z-30 p-10">
      <p className="absolute top-2 right-2 text-xs italic text-gray-400">{category}</p>
      <Image src={image} height={200} width={200} style="contain" alt=""/>
      <h4 className="my-3">{title}</h4>
      <div className="flex">
        {Array(Math.floor(rating.rate))
        .fill()
        .map((_, i) => (
          <StarIcon key={uuidv4} className="h-5 text-yellow-500" />
      ))}
      <span><a>{rating.count} reviews</a></span>

      </div>
      <p className="text-xs my-2 line-clamp-2">{description}</p>
      <div className="mb-5">
          <Currency quantity={price} currency="NOK" />
      </div>

      {hasPrime && (
        <div className="flex items-center space-x-2 -mt-5">
          <img className="w-12" src="https://links.papareact.com/fdw" alt="" />
        <p className="text-xs text-gray-500">FREE Next-day Delivery</p>
        </div>
      )}
      
      <button className="mt-auto button">Add to Basket</button>
    </div>
  )
}