import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITradeChannel } from '../trade-channel.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../trade-channel.test-samples';

import { TradeChannelService } from './trade-channel.service';

const requireRestSample: ITradeChannel = {
  ...sampleWithRequiredData,
};

describe('TradeChannel Service', () => {
  let service: TradeChannelService;
  let httpMock: HttpTestingController;
  let expectedResult: ITradeChannel | ITradeChannel[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TradeChannelService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a TradeChannel', () => {
      const tradeChannel = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(tradeChannel).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TradeChannel', () => {
      const tradeChannel = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(tradeChannel).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TradeChannel', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TradeChannel', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TradeChannel', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTradeChannelToCollectionIfMissing', () => {
      it('should add a TradeChannel to an empty array', () => {
        const tradeChannel: ITradeChannel = sampleWithRequiredData;
        expectedResult = service.addTradeChannelToCollectionIfMissing([], tradeChannel);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tradeChannel);
      });

      it('should not add a TradeChannel to an array that contains it', () => {
        const tradeChannel: ITradeChannel = sampleWithRequiredData;
        const tradeChannelCollection: ITradeChannel[] = [
          {
            ...tradeChannel,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTradeChannelToCollectionIfMissing(tradeChannelCollection, tradeChannel);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TradeChannel to an array that doesn't contain it", () => {
        const tradeChannel: ITradeChannel = sampleWithRequiredData;
        const tradeChannelCollection: ITradeChannel[] = [sampleWithPartialData];
        expectedResult = service.addTradeChannelToCollectionIfMissing(tradeChannelCollection, tradeChannel);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tradeChannel);
      });

      it('should add only unique TradeChannel to an array', () => {
        const tradeChannelArray: ITradeChannel[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tradeChannelCollection: ITradeChannel[] = [sampleWithRequiredData];
        expectedResult = service.addTradeChannelToCollectionIfMissing(tradeChannelCollection, ...tradeChannelArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tradeChannel: ITradeChannel = sampleWithRequiredData;
        const tradeChannel2: ITradeChannel = sampleWithPartialData;
        expectedResult = service.addTradeChannelToCollectionIfMissing([], tradeChannel, tradeChannel2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tradeChannel);
        expect(expectedResult).toContain(tradeChannel2);
      });

      it('should accept null and undefined values', () => {
        const tradeChannel: ITradeChannel = sampleWithRequiredData;
        expectedResult = service.addTradeChannelToCollectionIfMissing([], null, tradeChannel, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tradeChannel);
      });

      it('should return initial array if no TradeChannel is added', () => {
        const tradeChannelCollection: ITradeChannel[] = [sampleWithRequiredData];
        expectedResult = service.addTradeChannelToCollectionIfMissing(tradeChannelCollection, undefined, null);
        expect(expectedResult).toEqual(tradeChannelCollection);
      });
    });

    describe('compareTradeChannel', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTradeChannel(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTradeChannel(entity1, entity2);
        const compareResult2 = service.compareTradeChannel(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTradeChannel(entity1, entity2);
        const compareResult2 = service.compareTradeChannel(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTradeChannel(entity1, entity2);
        const compareResult2 = service.compareTradeChannel(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
