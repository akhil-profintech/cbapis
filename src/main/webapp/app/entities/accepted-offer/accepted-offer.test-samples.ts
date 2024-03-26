import dayjs from 'dayjs/esm';

import { IAcceptedOffer, NewAcceptedOffer } from './accepted-offer.model';

export const sampleWithRequiredData: IAcceptedOffer = {
  id: 28776,
  reqOffUlidId: 'ick manacle',
  value: 32348,
  reqAmount: 31239,
  marginPtg: 31905.24,
  marginValue: 25393,
  amountAftMargin: 24658,
  interestPtg: 18022.06,
  term: 26624,
  interestValue: 11613,
  netAmount: 6796,
  status: 'aw',
  offerDate: dayjs('2024-03-25'),
  offerAcceptedDate: dayjs('2024-03-25'),
};

export const sampleWithPartialData: IAcceptedOffer = {
  id: 3549,
  acceptedOfferUlidId: 'whether plus fortunately',
  reqOffUlidId: 'meh whereas capitulation',
  value: 28778,
  reqAmount: 23403,
  marginPtg: 29831.37,
  marginValue: 2904,
  amountAftMargin: 7452,
  interestPtg: 15041.43,
  term: 15021,
  interestValue: 17830,
  netAmount: 26882,
  status: 'including format towards',
  offerDate: dayjs('2024-03-25'),
  offerAcceptedDate: dayjs('2024-03-25'),
};

export const sampleWithFullData: IAcceptedOffer = {
  id: 7191,
  acceptedOfferUlidId: 'shameful atop deteriorate',
  acceptedOfferRefNo: 'recline huzzah',
  reqOffUlidId: 'recent last',
  value: 24010,
  reqAmount: 19778,
  marginPtg: 32447.47,
  marginValue: 20301,
  amountAftMargin: 27309,
  interestPtg: 5464.71,
  term: 23081,
  interestValue: 3847,
  netAmount: 11176,
  status: 'hence',
  offerDate: dayjs('2024-03-25'),
  offerAcceptedDate: dayjs('2024-03-25'),
};

export const sampleWithNewData: NewAcceptedOffer = {
  reqOffUlidId: 'bah',
  value: 3313,
  reqAmount: 29832,
  marginPtg: 10825.33,
  marginValue: 18411,
  amountAftMargin: 131,
  interestPtg: 24033.96,
  term: 21491,
  interestValue: 23768,
  netAmount: 18065,
  status: 'than to hearty',
  offerDate: dayjs('2024-03-25'),
  offerAcceptedDate: dayjs('2024-03-25'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
