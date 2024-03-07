import { IAnchorTraderPartner, NewAnchorTraderPartner } from './anchor-trader-partner.model';

export const sampleWithRequiredData: IAnchorTraderPartner = {
  id: 8683,
};

export const sampleWithPartialData: IAnchorTraderPartner = {
  id: 19389,
  panStatus: 'yet',
  name: 'cloak carefully oh',
  aadhar: 'concertina',
  aadharOtp: 'blister fundraising',
  aadharStatus: 'underneath torn',
  aadharName: 'fooey tame notwithstanding',
  aadharAddress: 'racer aha',
};

export const sampleWithFullData: IAnchorTraderPartner = {
  id: 26725,
  atpartnerId: 5956,
  atpartnerUlidId: 'lipstick spy',
  pan: 'scholarly',
  panStatus: 'psst lest infill',
  name: 'yahoo',
  aadhar: 'apron vegetate plus',
  aadharOtp: 'misunderstand amidst regularly',
  aadharStatus: 'bribe foresee beneath',
  aadharName: 'ensue wide-eyed',
  aadharAddress: 'ha',
};

export const sampleWithNewData: NewAnchorTraderPartner = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
