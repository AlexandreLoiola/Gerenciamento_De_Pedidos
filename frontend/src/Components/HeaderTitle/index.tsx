import React from 'react';

interface Iprops {
  title: any;
}

const HeaderTitle: React.FC<Iprops> = (props) => {
  return (
    <h1>{props.title}</h1>
  );
}

export default HeaderTitle;