import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITradePartner } from '../trade-partner.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../trade-partner.test-samples';

import { TradePartnerService } from './trade-partner.service';

const requireRestSample: ITradePartner = {
  ...sampleWithRequiredData,
};

describe('TradePartner Service', () => {
  let service: TradePartnerService;
  let httpMock: HttpTestingController;
  let expectedResult: ITradePartner | ITradePartner[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TradePartnerService);
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

    it('should create a TradePartner', () => {
      const tradePartner = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(tradePartner).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TradePartner', () => {
      const tradePartner = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(tradePartner).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TradePartner', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TradePartner', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TradePartner', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTradePartnerToCollectionIfMissing', () => {
      it('should add a TradePartner to an empty array', () => {
        const tradePartner: ITradePartner = sampleWithRequiredData;
        expectedResult = service.addTradePartnerToCollectionIfMissing([], tradePartner);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tradePartner);
      });

      it('should not add a TradePartner to an array that contains it', () => {
        const tradePartner: ITradePartner = sampleWithRequiredData;
        const tradePartnerCollection: ITradePartner[] = [
          {
            ...tradePartner,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTradePartnerToCollectionIfMissing(tradePartnerCollection, tradePartner);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TradePartner to an array that doesn't contain it", () => {
        const tradePartner: ITradePartner = sampleWithRequiredData;
        const tradePartnerCollection: ITradePartner[] = [sampleWithPartialData];
        expectedResult = service.addTradePartnerToCollectionIfMissing(tradePartnerCollection, tradePartner);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tradePartner);
      });

      it('should add only unique TradePartner to an array', () => {
        const tradePartnerArray: ITradePartner[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tradePartnerCollection: ITradePartner[] = [sampleWithRequiredData];
        expectedResult = service.addTradePartnerToCollectionIfMissing(tradePartnerCollection, ...tradePartnerArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tradePartner: ITradePartner = sampleWithRequiredData;
        const tradePartner2: ITradePartner = sampleWithPartialData;
        expectedResult = service.addTradePartnerToCollectionIfMissing([], tradePartner, tradePartner2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tradePartner);
        expect(expectedResult).toContain(tradePartner2);
      });

      it('should accept null and undefined values', () => {
        const tradePartner: ITradePartner = sampleWithRequiredData;
        expectedResult = service.addTradePartnerToCollectionIfMissing([], null, tradePartner, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tradePartner);
      });

      it('should return initial array if no TradePartner is added', () => {
        const tradePartnerCollection: ITradePartner[] = [sampleWithRequiredData];
        expectedResult = service.addTradePartnerToCollectionIfMissing(tradePartnerCollection, undefined, null);
        expect(expectedResult).toEqual(tradePartnerCollection);
      });
    });

    describe('compareTradePartner', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTradePartner(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTradePartner(entity1, entity2);
        const compareResult2 = service.compareTradePartner(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTradePartner(entity1, entity2);
        const compareResult2 = service.compareTradePartner(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTradePartner(entity1, entity2);
        const compareResult2 = service.compareTradePartner(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
