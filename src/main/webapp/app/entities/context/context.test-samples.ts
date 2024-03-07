import { IContext, NewContext } from './context.model';

export const sampleWithRequiredData: IContext = {
  id: 1633,
  transactionDate: 'miscount',
  action: 'far-flung gadzooks babyish',
  userId: 'zebra noisy writhe',
  tenantId: 'prophesy except',
  cpCode: 'light',
  msgpayload: 'lending oddball along',
};

export const sampleWithPartialData: IContext = {
  id: 25978,
  transactionId: 'instead supposing',
  transactionDate: 'trick times sunny',
  action: 'than lest',
  userId: 'triumphantly eek paranoia',
  tenantId: 'epee what',
  cpCode: 'modulate bleakly if',
  msgpayload: 'how clout cot',
};

export const sampleWithFullData: IContext = {
  id: 9955,
  transactionId: 'insistent including',
  transactionDate: 'appease once whoa',
  action: 'questioningly unique',
  userId: 'concerning ravage now',
  tenantId: 'yum',
  cpCode: 'righteously expostulate',
  msgpayload: 'lighten unto frolic',
};

export const sampleWithNewData: NewContext = {
  transactionDate: 'afore than nursery',
  action: 'slog definite lovingly',
  userId: 'promenade silver opposite',
  tenantId: 'load',
  cpCode: 'nominate what',
  msgpayload: 'weary quarrelsome impassioned',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
