import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAnchorTraderPartner } from '../anchor-trader-partner.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../anchor-trader-partner.test-samples';

import { AnchorTraderPartnerService } from './anchor-trader-partner.service';

const requireRestSample: IAnchorTraderPartner = {
  ...sampleWithRequiredData,
};

describe('AnchorTraderPartner Service', () => {
  let service: AnchorTraderPartnerService;
  let httpMock: HttpTestingController;
  let expectedResult: IAnchorTraderPartner | IAnchorTraderPartner[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AnchorTraderPartnerService);
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

    it('should create a AnchorTraderPartner', () => {
      const anchorTraderPartner = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(anchorTraderPartner).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AnchorTraderPartner', () => {
      const anchorTraderPartner = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(anchorTraderPartner).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AnchorTraderPartner', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AnchorTraderPartner', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AnchorTraderPartner', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAnchorTraderPartnerToCollectionIfMissing', () => {
      it('should add a AnchorTraderPartner to an empty array', () => {
        const anchorTraderPartner: IAnchorTraderPartner = sampleWithRequiredData;
        expectedResult = service.addAnchorTraderPartnerToCollectionIfMissing([], anchorTraderPartner);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(anchorTraderPartner);
      });

      it('should not add a AnchorTraderPartner to an array that contains it', () => {
        const anchorTraderPartner: IAnchorTraderPartner = sampleWithRequiredData;
        const anchorTraderPartnerCollection: IAnchorTraderPartner[] = [
          {
            ...anchorTraderPartner,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAnchorTraderPartnerToCollectionIfMissing(anchorTraderPartnerCollection, anchorTraderPartner);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AnchorTraderPartner to an array that doesn't contain it", () => {
        const anchorTraderPartner: IAnchorTraderPartner = sampleWithRequiredData;
        const anchorTraderPartnerCollection: IAnchorTraderPartner[] = [sampleWithPartialData];
        expectedResult = service.addAnchorTraderPartnerToCollectionIfMissing(anchorTraderPartnerCollection, anchorTraderPartner);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(anchorTraderPartner);
      });

      it('should add only unique AnchorTraderPartner to an array', () => {
        const anchorTraderPartnerArray: IAnchorTraderPartner[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const anchorTraderPartnerCollection: IAnchorTraderPartner[] = [sampleWithRequiredData];
        expectedResult = service.addAnchorTraderPartnerToCollectionIfMissing(anchorTraderPartnerCollection, ...anchorTraderPartnerArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const anchorTraderPartner: IAnchorTraderPartner = sampleWithRequiredData;
        const anchorTraderPartner2: IAnchorTraderPartner = sampleWithPartialData;
        expectedResult = service.addAnchorTraderPartnerToCollectionIfMissing([], anchorTraderPartner, anchorTraderPartner2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(anchorTraderPartner);
        expect(expectedResult).toContain(anchorTraderPartner2);
      });

      it('should accept null and undefined values', () => {
        const anchorTraderPartner: IAnchorTraderPartner = sampleWithRequiredData;
        expectedResult = service.addAnchorTraderPartnerToCollectionIfMissing([], null, anchorTraderPartner, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(anchorTraderPartner);
      });

      it('should return initial array if no AnchorTraderPartner is added', () => {
        const anchorTraderPartnerCollection: IAnchorTraderPartner[] = [sampleWithRequiredData];
        expectedResult = service.addAnchorTraderPartnerToCollectionIfMissing(anchorTraderPartnerCollection, undefined, null);
        expect(expectedResult).toEqual(anchorTraderPartnerCollection);
      });
    });

    describe('compareAnchorTraderPartner', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAnchorTraderPartner(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAnchorTraderPartner(entity1, entity2);
        const compareResult2 = service.compareAnchorTraderPartner(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAnchorTraderPartner(entity1, entity2);
        const compareResult2 = service.compareAnchorTraderPartner(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAnchorTraderPartner(entity1, entity2);
        const compareResult2 = service.compareAnchorTraderPartner(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
