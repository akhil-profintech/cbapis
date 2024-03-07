import { ICreditAccountDetails, NewCreditAccountDetails } from './credit-account-details.model';

export const sampleWithRequiredData: ICreditAccountDetails = {
  id: 19793,
  tenantId: 16384,
  customerId: 31420,
  accName: 'flawless composed even',
  ifscCode: 'honestly sternly psst',
  accNumber: 28329,
};

export const sampleWithPartialData: ICreditAccountDetails = {
  id: 13744,
  creditAccDetailsId: 1298,
  creditAccountDetailsUlidId: 'fascinate whoever fresco',
  tenantId: 12088,
  customerId: 19324,
  accName: 'stravaig pantologist cement',
  ifscCode: 'inside shyly persist',
  accNumber: 32429,
  destinationAccountName: 'but or',
};

export const sampleWithFullData: ICreditAccountDetails = {
  id: 15554,
  creditAccDetailsId: 26630,
  creditAccountDetailsUlidId: 'pfft velvety notwithstanding',
  tenantId: 17917,
  customerId: 17344,
  accName: 'which until',
  ifscCode: 'schuss provided',
  accNumber: 24686,
  destinationAccountName: 'ample bah improbable',
  destinationAccountNumber: 'incidentally atop',
};

export const sampleWithNewData: NewCreditAccountDetails = {
  tenantId: 21403,
  customerId: 8214,
  accName: 'halve after',
  ifscCode: 'ack',
  accNumber: 18922,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
