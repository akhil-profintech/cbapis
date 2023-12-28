import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICREHighlights } from '../cre-highlights.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../cre-highlights.test-samples';

import { CREHighlightsService } from './cre-highlights.service';

const requireRestSample: ICREHighlights = {
  ...sampleWithRequiredData,
};

describe('CREHighlights Service', () => {
  let service: CREHighlightsService;
  let httpMock: HttpTestingController;
  let expectedResult: ICREHighlights | ICREHighlights[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CREHighlightsService);
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

    it('should create a CREHighlights', () => {
      const cREHighlights = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(cREHighlights).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CREHighlights', () => {
      const cREHighlights = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(cREHighlights).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CREHighlights', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CREHighlights', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CREHighlights', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCREHighlightsToCollectionIfMissing', () => {
      it('should add a CREHighlights to an empty array', () => {
        const cREHighlights: ICREHighlights = sampleWithRequiredData;
        expectedResult = service.addCREHighlightsToCollectionIfMissing([], cREHighlights);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cREHighlights);
      });

      it('should not add a CREHighlights to an array that contains it', () => {
        const cREHighlights: ICREHighlights = sampleWithRequiredData;
        const cREHighlightsCollection: ICREHighlights[] = [
          {
            ...cREHighlights,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCREHighlightsToCollectionIfMissing(cREHighlightsCollection, cREHighlights);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CREHighlights to an array that doesn't contain it", () => {
        const cREHighlights: ICREHighlights = sampleWithRequiredData;
        const cREHighlightsCollection: ICREHighlights[] = [sampleWithPartialData];
        expectedResult = service.addCREHighlightsToCollectionIfMissing(cREHighlightsCollection, cREHighlights);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cREHighlights);
      });

      it('should add only unique CREHighlights to an array', () => {
        const cREHighlightsArray: ICREHighlights[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const cREHighlightsCollection: ICREHighlights[] = [sampleWithRequiredData];
        expectedResult = service.addCREHighlightsToCollectionIfMissing(cREHighlightsCollection, ...cREHighlightsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const cREHighlights: ICREHighlights = sampleWithRequiredData;
        const cREHighlights2: ICREHighlights = sampleWithPartialData;
        expectedResult = service.addCREHighlightsToCollectionIfMissing([], cREHighlights, cREHighlights2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cREHighlights);
        expect(expectedResult).toContain(cREHighlights2);
      });

      it('should accept null and undefined values', () => {
        const cREHighlights: ICREHighlights = sampleWithRequiredData;
        expectedResult = service.addCREHighlightsToCollectionIfMissing([], null, cREHighlights, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cREHighlights);
      });

      it('should return initial array if no CREHighlights is added', () => {
        const cREHighlightsCollection: ICREHighlights[] = [sampleWithRequiredData];
        expectedResult = service.addCREHighlightsToCollectionIfMissing(cREHighlightsCollection, undefined, null);
        expect(expectedResult).toEqual(cREHighlightsCollection);
      });
    });

    describe('compareCREHighlights', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCREHighlights(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCREHighlights(entity1, entity2);
        const compareResult2 = service.compareCREHighlights(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCREHighlights(entity1, entity2);
        const compareResult2 = service.compareCREHighlights(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCREHighlights(entity1, entity2);
        const compareResult2 = service.compareCREHighlights(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
