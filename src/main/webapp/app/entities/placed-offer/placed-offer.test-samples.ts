import dayjs from 'dayjs/esm';

import { IPlacedOffer, NewPlacedOffer } from './placed-offer.model';

export const sampleWithRequiredData: IPlacedOffer = {
  id: 1410,
  value: 6525,
  reqAmount: 2406,
  marginPtg: 13470.41,
  marginValue: 9526,
  amountAftMargin: 25832,
  interestPtg: 18907.37,
  term: 25013,
  interestValue: 20755,
  netAmount: 25360,
  offerDate: dayjs('2023-12-28'),
};

export const sampleWithPartialData: IPlacedOffer = {
  id: 28369,
  reqOffId: 'likewise intently',
  requestOfferRefNo: 'once via preservation',
  value: 9167,
  reqAmount: 30241,
  marginPtg: 9470.3,
  marginValue: 29307,
  amountAftMargin: 644,
  interestPtg: 17279.41,
  term: 19994,
  interestValue: 31819,
  netAmount: 11956,
  offerDate: dayjs('2023-12-27'),
  requestId: 'whoa',
};

export const sampleWithFullData: IPlacedOffer = {
  id: 17272,
  reqOffId: 'before',
  placedOfferId: 'zowie behold',
  placedOfferRefNo: 'extricate longingly',
  requestOfferRefNo: 'sanctuary vogue',
  value: 23882,
  reqAmount: 5032,
  marginPtg: 1914.16,
  marginValue: 2536,
  amountAftMargin: 21996,
  interestPtg: 26825.51,
  term: 4511,
  interestValue: 11369,
  netAmount: 27639,
  offerDate: dayjs('2023-12-28'),
  requestId: 'water',
  placedOfferDate: dayjs('2023-12-28'),
  anchorTrader: 'so consequently tensely',
  tradePartner: 'wisely madden earn',
  disbursalAmount: 'frightfully potentially',
  status: 'glum hm',
};

export const sampleWithNewData: NewPlacedOffer = {
  value: 18901,
  reqAmount: 16600,
  marginPtg: 25965.78,
  marginValue: 27887,
  amountAftMargin: 31390,
  interestPtg: 11631.06,
  term: 13245,
  interestValue: 2202,
  netAmount: 13782,
  offerDate: dayjs('2023-12-27'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
