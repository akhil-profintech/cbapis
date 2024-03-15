import dayjs from 'dayjs/esm';

import { IPlacedOffer, NewPlacedOffer } from './placed-offer.model';

export const sampleWithRequiredData: IPlacedOffer = {
  id: 23122,
  value: 1410,
  reqAmount: 6525,
  marginPtg: 2406.8,
  marginValue: 13470,
  amountAftMargin: 9526,
  interestPtg: 25831.84,
  term: 18907,
  interestValue: 25013,
  netAmount: 20755,
  status: 'yieldingly adventurous professionalize',
  offerDate: dayjs('2024-03-14'),
  disbursalAmount: 'beneath for',
};

export const sampleWithPartialData: IPlacedOffer = {
  id: 17402,
  reqOffUlidId: 'unlike',
  requestOfferRefNo: 'toward',
  value: 15541,
  reqAmount: 5440,
  marginPtg: 16161.38,
  marginValue: 8594,
  amountAftMargin: 14215,
  interestPtg: 4143.79,
  term: 17272,
  interestValue: 5334,
  netAmount: 21857,
  status: 'youthfully authorized frank',
  offerDate: dayjs('2024-03-14'),
  anchorTrader: 'metal where',
  tradePartner: 'overconfidently huzzah',
  disbursalAmount: 'aha',
};

export const sampleWithFullData: IPlacedOffer = {
  id: 3101,
  placedOfferUlidId: 'even circa searchingly',
  placedOfferRefNo: 'tensely',
  reqOffUlidId: 'wisely madden earn',
  requestOfferRefNo: 'frightfully potentially',
  value: 14957,
  reqAmount: 13136,
  marginPtg: 8819.3,
  marginValue: 23680,
  amountAftMargin: 25989,
  interestPtg: 12537.85,
  term: 24705,
  interestValue: 11144,
  netAmount: 27335,
  status: 'including',
  offerDate: dayjs('2024-03-14'),
  placedOfferDate: dayjs('2024-03-14'),
  anchorTrader: 'ah till inquisitively',
  tradePartner: 'heavy',
  disbursalAmount: 'what glint graceful',
};

export const sampleWithNewData: NewPlacedOffer = {
  value: 28544,
  reqAmount: 28429,
  marginPtg: 4598.46,
  marginValue: 24789,
  amountAftMargin: 9464,
  interestPtg: 28786.82,
  term: 15395,
  interestValue: 30837,
  netAmount: 15625,
  status: 'healthily',
  offerDate: dayjs('2024-03-14'),
  disbursalAmount: 'violently masculine',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
