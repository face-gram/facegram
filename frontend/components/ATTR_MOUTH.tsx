const ATTR_MOUTH = [
  {
    level: 1,
    id: "type",
    name: "유형",
    buttons: ["분류없음", "보통형", "돌출형", "오목형"],
  },
  {
    level: 1,
    id: "size",
    name: "크기",
    buttons: ["크다", "보통", "작다"],
  },
  {
    level: 2,
    id: "shape",
    name: "입술 모양",
    buttons: ["분류없음", "ㅡ모양", "V모양", "M모양", "O모양"],
  },
  {
    level: 2,
    id: "thick",
    name: "입술 두께",
    buttons: ["두껍다", "보통", "얇다"],
  },
  {
    level: 2,
    id: "ratio",
    name: "입술 비율",
    buttons: ["아랫입술이 크다", "윗입술이 크다", "위아래가 같다"],
  },
  {
    level: 2,
    id: "side",
    name: "입 꼬리",
    buttons: ["올라감", "보통", "처짐"],
  },
  {
    level: 3,
    id: "line",
    name: "인중선",
    buttons: ["분류없음", "뚜렷함", "흐림"],
  },
  {
    level: 0,
    id: "description",
    name: "주요설명",
    buttons: [],
  },
];

export default ATTR_MOUTH;

