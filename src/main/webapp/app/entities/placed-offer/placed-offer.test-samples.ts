import dayjs from 'dayjs/esm';

import { IPlacedOffer, NewPlacedOffer } from './placed-offer.model';

export const sampleWithRequiredData: IPlacedOffer = {
  id: 32420,
  value: 23122,
  reqAmount: 1410,
  marginPtg: 6525.31,
  marginValue: 2406,
  amountAftMargin: 13470,
  interestPtg: 9526.69,
  term: 25832,
  interestValue: 18907,
  netAmount: 25013,
  offerDate: dayjs('2023-12-27'),
};

export const sampleWithPartialData: IPlacedOffer = {
  id: 24503,
  placedOfferRefNo: 'questioningly lustrous beneath',
  value: 8671,
  reqAmount: 4915,
  marginPtg: 9620.63,
  marginValue: 29764,
  amountAftMargin: 16353,
  interestPtg: 13708.59,
  term: 6872,
  interestValue: 25511,
  netAmount: 25050,
  status: 'bind',
  offerDate: dayjs('2023-12-27'),
  anchorTrader: 'than defiantly planter',
  tradePartner: 'youthfully authorized frank',
};

export const sampleWithFullData: IPlacedOffer = {
  id: 24690,
  reqOffId: 'metal where',
  placedOfferRefNo: 'overconfidently huzzah',
  value: 1914,
  reqAmount: 2536,
  marginPtg: 21995.63,
  marginValue: 26826,
  amountAftMargin: 4511,
  interestPtg: 11369.27,
  term: 27639,
  interestValue: 12876,
  netAmount: 3101,
  status: 'even circa searchingly',
  offerDate: dayjs('2023-12-28'),
  requestId: 'whether',
  placedOfferDate: dayjs('2023-12-27'),
  anchorTrader: 'into',
  tradePartner: 'across',
  disbursalAmount: 'finally',
};

export const sampleWithNewData: NewPlacedOffer = {
  value: 1354,
  reqAmount: 23322,
  marginPtg: 7717.54,
  marginValue: 9014,
  amountAftMargin: 10404,
  interestPtg: 22630.71,
  term: 20711,
  interestValue: 3229,
  netAmount: 1566,
  offerDate: dayjs('2023-12-28'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
