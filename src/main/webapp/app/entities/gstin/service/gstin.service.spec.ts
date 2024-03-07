import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IGstin } from '../gstin.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../gstin.test-samples';

import { GstinService } from './gstin.service';

const requireRestSample: IGstin = {
  ...sampleWithRequiredData,
};

describe('Gstin Service', () => {
  let service: GstinService;
  let httpMock: HttpTestingController;
  let expectedResult: IGstin | IGstin[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(GstinService);
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

    it('should create a Gstin', () => {
      const gstin = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(gstin).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Gstin', () => {
      const gstin = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(gstin).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Gstin', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Gstin', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Gstin', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addGstinToCollectionIfMissing', () => {
      it('should add a Gstin to an empty array', () => {
        const gstin: IGstin = sampleWithRequiredData;
        expectedResult = service.addGstinToCollectionIfMissing([], gstin);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(gstin);
      });

      it('should not add a Gstin to an array that contains it', () => {
        const gstin: IGstin = sampleWithRequiredData;
        const gstinCollection: IGstin[] = [
          {
            ...gstin,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addGstinToCollectionIfMissing(gstinCollection, gstin);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Gstin to an array that doesn't contain it", () => {
        const gstin: IGstin = sampleWithRequiredData;
        const gstinCollection: IGstin[] = [sampleWithPartialData];
        expectedResult = service.addGstinToCollectionIfMissing(gstinCollection, gstin);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(gstin);
      });

      it('should add only unique Gstin to an array', () => {
        const gstinArray: IGstin[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const gstinCollection: IGstin[] = [sampleWithRequiredData];
        expectedResult = service.addGstinToCollectionIfMissing(gstinCollection, ...gstinArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const gstin: IGstin = sampleWithRequiredData;
        const gstin2: IGstin = sampleWithPartialData;
        expectedResult = service.addGstinToCollectionIfMissing([], gstin, gstin2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(gstin);
        expect(expectedResult).toContain(gstin2);
      });

      it('should accept null and undefined values', () => {
        const gstin: IGstin = sampleWithRequiredData;
        expectedResult = service.addGstinToCollectionIfMissing([], null, gstin, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(gstin);
      });

      it('should return initial array if no Gstin is added', () => {
        const gstinCollection: IGstin[] = [sampleWithRequiredData];
        expectedResult = service.addGstinToCollectionIfMissing(gstinCollection, undefined, null);
        expect(expectedResult).toEqual(gstinCollection);
      });
    });

    describe('compareGstin', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareGstin(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareGstin(entity1, entity2);
        const compareResult2 = service.compareGstin(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareGstin(entity1, entity2);
        const compareResult2 = service.compareGstin(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareGstin(entity1, entity2);
        const compareResult2 = service.compareGstin(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
