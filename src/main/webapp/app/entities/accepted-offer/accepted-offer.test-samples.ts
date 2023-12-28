import dayjs from 'dayjs/esm';

import { IAcceptedOffer, NewAcceptedOffer } from './accepted-offer.model';

export const sampleWithRequiredData: IAcceptedOffer = {
  id: 28776,
  reqOffId: 15648,
  value: 8961,
  reqAmount: 26045,
  marginPtg: 31740.81,
  marginValue: 11089,
  amountAftMargin: 703,
  interestPtg: 12001.23,
  term: 30854,
  interestValue: 2844,
  netAmount: 16064,
  status: 'winged afford',
  offerDate: dayjs('2023-12-27'),
  offerAcceptedDate: dayjs('2023-12-27'),
};

export const sampleWithPartialData: IAcceptedOffer = {
  id: 30480,
  offerId: 'until whether',
  acceptedOfferRefNo: 'recess',
  reqOffId: 26263,
  value: 18207,
  reqAmount: 7556,
  marginPtg: 10885.91,
  marginValue: 1050,
  amountAftMargin: 8551,
  interestPtg: 30149.19,
  term: 22044,
  interestValue: 22896,
  netAmount: 9009,
  status: 'freon',
  offerDate: dayjs('2023-12-27'),
  offerAcceptedDate: dayjs('2023-12-28'),
};

export const sampleWithFullData: IAcceptedOffer = {
  id: 26417,
  offerId: 'sweatshop provided brr',
  acceptedOfferRefNo: 'subedit geez logic',
  reqOffId: 24994,
  value: 24814,
  reqAmount: 11535,
  marginPtg: 28125.49,
  marginValue: 24278,
  amountAftMargin: 15286,
  interestPtg: 26830.34,
  term: 18652,
  interestValue: 3945,
  netAmount: 13685,
  status: 'innocent',
  offerDate: dayjs('2023-12-28'),
  offerAcceptedDate: dayjs('2023-12-27'),
};

export const sampleWithNewData: NewAcceptedOffer = {
  reqOffId: 25191,
  value: 4201,
  reqAmount: 17398,
  marginPtg: 14321.07,
  marginValue: 13400,
  amountAftMargin: 19489,
  interestPtg: 22780.46,
  term: 26281,
  interestValue: 14402,
  netAmount: 1346,
  status: 'unnaturally',
  offerDate: dayjs('2023-12-27'),
  offerAcceptedDate: dayjs('2023-12-27'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
