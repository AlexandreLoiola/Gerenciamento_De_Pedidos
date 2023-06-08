import React from "react"
import { Button } from './styles';

interface Iprops {
  title: String;
}

const SubmitButton: React.FC<Iprops> = (props) => {
    return (
        <Button type="submit">{props.title}</Button>
    )
  };

export default SubmitButton;