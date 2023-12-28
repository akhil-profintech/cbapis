import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAnchorTrader } from '../anchor-trader.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../anchor-trader.test-samples';

import { AnchorTraderService } from './anchor-trader.service';

const requireRestSample: IAnchorTrader = {
  ...sampleWithRequiredData,
};

describe('AnchorTrader Service', () => {
  let service: AnchorTraderService;
  let httpMock: HttpTestingController;
  let expectedResult: IAnchorTrader | IAnchorTrader[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AnchorTraderService);
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

    it('should create a AnchorTrader', () => {
      const anchorTrader = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(anchorTrader).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AnchorTrader', () => {
      const anchorTrader = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(anchorTrader).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AnchorTrader', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AnchorTrader', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AnchorTrader', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAnchorTraderToCollectionIfMissing', () => {
      it('should add a AnchorTrader to an empty array', () => {
        const anchorTrader: IAnchorTrader = sampleWithRequiredData;
        expectedResult = service.addAnchorTraderToCollectionIfMissing([], anchorTrader);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(anchorTrader);
      });

      it('should not add a AnchorTrader to an array that contains it', () => {
        const anchorTrader: IAnchorTrader = sampleWithRequiredData;
        const anchorTraderCollection: IAnchorTrader[] = [
          {
            ...anchorTrader,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAnchorTraderToCollectionIfMissing(anchorTraderCollection, anchorTrader);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AnchorTrader to an array that doesn't contain it", () => {
        const anchorTrader: IAnchorTrader = sampleWithRequiredData;
        const anchorTraderCollection: IAnchorTrader[] = [sampleWithPartialData];
        expectedResult = service.addAnchorTraderToCollectionIfMissing(anchorTraderCollection, anchorTrader);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(anchorTrader);
      });

      it('should add only unique AnchorTrader to an array', () => {
        const anchorTraderArray: IAnchorTrader[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const anchorTraderCollection: IAnchorTrader[] = [sampleWithRequiredData];
        expectedResult = service.addAnchorTraderToCollectionIfMissing(anchorTraderCollection, ...anchorTraderArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const anchorTrader: IAnchorTrader = sampleWithRequiredData;
        const anchorTrader2: IAnchorTrader = sampleWithPartialData;
        expectedResult = service.addAnchorTraderToCollectionIfMissing([], anchorTrader, anchorTrader2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(anchorTrader);
        expect(expectedResult).toContain(anchorTrader2);
      });

      it('should accept null and undefined values', () => {
        const anchorTrader: IAnchorTrader = sampleWithRequiredData;
        expectedResult = service.addAnchorTraderToCollectionIfMissing([], null, anchorTrader, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(anchorTrader);
      });

      it('should return initial array if no AnchorTrader is added', () => {
        const anchorTraderCollection: IAnchorTrader[] = [sampleWithRequiredData];
        expectedResult = service.addAnchorTraderToCollectionIfMissing(anchorTraderCollection, undefined, null);
        expect(expectedResult).toEqual(anchorTraderCollection);
      });
    });

    describe('compareAnchorTrader', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAnchorTrader(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAnchorTrader(entity1, entity2);
        const compareResult2 = service.compareAnchorTrader(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAnchorTrader(entity1, entity2);
        const compareResult2 = service.compareAnchorTrader(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAnchorTrader(entity1, entity2);
        const compareResult2 = service.compareAnchorTrader(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
