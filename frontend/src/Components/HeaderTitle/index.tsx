import React from 'react';

interface Iprops {
  title: String;
}

const HeaderTitle: React.FC<Iprops> = (props) => {
  return (
    <h1>{props.title}</h1>
  );
}

export default HeaderTitle;