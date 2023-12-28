import { ICreditAccountDetails, NewCreditAccountDetails } from './credit-account-details.model';

export const sampleWithRequiredData: ICreditAccountDetails = {
  id: 32681,
  creditAccDetailsId: 4170,
  tenantId: 2977,
  customerId: 19793,
  accName: 'polite round',
  ifscCode: 'alternative sniveling toreador',
  accNumber: 3979,
};

export const sampleWithPartialData: ICreditAccountDetails = {
  id: 25362,
  creditAccDetailsId: 24874,
  tenantId: 30175,
  customerId: 27288,
  accName: 'hopelessly',
  ifscCode: 'walker closet since',
  accNumber: 15513,
};

export const sampleWithFullData: ICreditAccountDetails = {
  id: 22716,
  creditAccDetailsId: 12024,
  tenantId: 12088,
  customerId: 19324,
  accName: 'stravaig pantologist cement',
  ifscCode: 'inside shyly persist',
  accNumber: 32429,
};

export const sampleWithNewData: NewCreditAccountDetails = {
  creditAccDetailsId: 14292,
  tenantId: 2406,
  customerId: 25622,
  accName: 'concerning socialism pfft',
  ifscCode: 'private moonwalk noisily',
  accNumber: 2756,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
